document.addEventListener('DOMContentLoaded', () =>{
    document.querySelectorAll(".voltar").forEach((element)=>{
        let href =(element.getAttribute("href") || "").trim();

        if(href.length <= 0 || href === "#"){
            href = document.referrer;
            if(!href || href.trim().length <=0){
                href = window.location.origin + "/proju"
            }

            element.setAttribute("href", href);
        }
    });
});