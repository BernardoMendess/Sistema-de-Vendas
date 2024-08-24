
restApi = {
    get: (url, objs)=>{
        if(!objs || Object.keys(objs).length !== 0 || objs.constructor !== Object){
            url = url + '?' + new URLSearchParams(objs)
        }
        return fetch(url, {
            cors:false,
            headers:{
                'Access-Control-Allow-Origin': '*',
               'Content-Type': 'application/json'
            }
        })
            .then((response)=>{
                // The API call was successful!
                if (response.ok) {
                    return response.json();
                } else {
                    return Promise.reject(response);
                }
            })
    },
    post: (url, content)=>{
        var token  = document.querySelector("meta[name='_csrf']").getAttribute("content");
        var header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");
        return fetch(
            url,
            {
                method:'POST',
                headers: {
                   [header]: token,
                   "Content-Type": "application/json"
                },
                body: JSON.stringify(content)
            }
        )
         .then((response)=>{
                        // The API call was successful!
                        if (response.ok) {
                            return response.json();
                        } else {
                            return Promise.reject(response);
                        }
                    })
    },
    DELETE: (url, content)=>{
        var token = document.querySelector("meta[name='_csrf']").getAttribute("content");
        var header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");
        return fetch(
            url,
            {
                method:'DELETE',
                headers: {
                   [header]: token,
                   "Content-Type": "application/json"
                },
                body: JSON.stringify(content)
            }
        )
         .then((response)=>{
                        // The API call was successful!
                        if (response.ok) {
                            return response.json();
                        } else {
                            return Promise.reject(response);
                        }
                    })
    }
}