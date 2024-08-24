(function(){

    const ifAttribute = (element, attributeName, func)=>{
        const value = element.getAttribute(attributeName)
        if(value && value.trim().length>0){
            func(value);
        }
    }

    const getTagConfig = (element)=>{
        const config = {
            data:{threshold:4},
            filter:(e)=>true,
            validCriteria: (e)=>true,
            targetInput:{name:element.id + "-input-target", value:""},
            formatSelectedValue: (e)=>JSON.stringify(e),
            keyName: 'id'
        }


        ifAttribute(element, "data-rest-autocomplete-url"                    , (value)=>config.url=value)
        ifAttribute(element, "data-rest-autocomplete-target-input-name"      , (value)=>config.targetInput.name=value)
        ifAttribute(element, "data-rest-autocomplete-target-input-value"     , (value)=>config.targetInput.value=value)
        ifAttribute(element, "data-rest-autocomplete-data-key-name"           , (value)=>config.keyName=value)
        ifAttribute(element, "data-rest-autocomplete-view-format"            , (value)=>config.viewFormat= eval(value))
        ifAttribute(element, "data-rest-autocomplete-filter"                 , (value)=>config.filter= eval(value))
        ifAttribute(element, "data-rest-autocomplete-data-threshold"         , (value)=>config.data.threshold=value)


        return config;
    }
    var RestAutoComplete = function(element, config){

        config = Object.assign(getTagConfig(element), config || {});

        const getSrc = function getSrc(url){
            if(url && url.toLowerCase().startsWith("data:")){
                const jsonParsed = JSON.parse(decodeURI(url).replace("data:", ""))
                                    .map(x=>{x.toString = ()=>JSON.stringify(x); return x;});
                //use hardcoded data
                if(!Array.isArray(jsonParsed)) throw "Não é um array o src do tipo 'data:'";

                return jsonParsed;
            }else{
                return async (_query)=>{
                            const result = await restApi.get(config.url, {query:_query})
                                            .then((data)=>{return data.filter(config.filter)})
                                            .catch((e)=>{console.log("Error on query rest api:  " + e, e)});
                            return await result;
                };
            }
        };


        const src = getSrc(config.url);

        this.autocompleteJS = new autoComplete({
            selector: ()=>element,
            data: {
                src: src,
                threshold: config.data.threshold,
            },
            debounce: 300,
            searchEngine: (Array.isArray(src))?'strict':(query, record)=>record,
            cache: false,
            resultsList: {
                element: (list, data) => {
                  if (!data.results.length) {
                    const info = document.createElement("p");
                    info.innerHTML = `<strong>Nenhum elemento encontrado</strong>`;
                    list.prepend(info);
                  }
                },
                noResults: true
            },
            resultItem: {
                element: (item, data) => item.innerHTML = config.viewFormat(data.value)
            },
            events: {
                input: {
                    focus: () => {
                       if (this.autocompleteJS.input.value.length) {
                          this.autocompleteJS.input.value = '';
                          this.inputHidden.value='';
                       }
                    },
                    selection: (event)=>{
                       this.autocompleteJS.input.value = config.viewFormat(event.detail.selection.value);

                       //salva o id
                       this.inputHidden.value = event.detail.selection.value[config.keyName]
                    }
                }
            }
        });

        //cria input[hidden]
        const inputHiddenList = this.autocompleteJS.wrapper.querySelector("input[hidden]")

        this.inputHidden = inputHiddenList || (()=>{
            const i = document.createElement("input");
            i.setAttribute("type" , "hidden");
            i.setAttribute("name" , config.targetInput.name);
            i.setAttribute("value", config.targetInput.value);
            return i;
        })();

        this.autocompleteJS.wrapper.appendChild(this.inputHidden);
    }

    //pega todos que tenham a classe rest-autocomplete
    document.querySelectorAll(".rest-autocomplete")
        .forEach(element=>{new RestAutoComplete(element)});
})()