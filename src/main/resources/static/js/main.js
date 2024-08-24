document.addEventListener('DOMContentLoaded', () =>{
    document.getElementById("btn-logout")
        .addEventListener("click",
            (e)=>e.target.closest("form").submit()
        );
});