$(document).ready(function() {
    $('#autorId').select2({
        minimumInputLength: 3,
        ajax: {
            url: '/biblioteca/api/autores',
            data: function (params) {
                return {
                    busca: params.term,
                };
            },
            processResults: function (data) {
                return {
                    results: data.map(function(item) {
                        return {
                            id: item.id,
                            text: item.nome,
                        };
                    }),
                };
            },
        },
    });

    $('#editoraId').select2({
        minimumInputLength: 3,
        ajax: {
            url: '/biblioteca/api/editoras',
            data: function (params) {
                return {
                    busca: params.term,
                };
            },
            processResults: function (data) {
                return {
                    results: data.map(function(item) {
                        return {
                            id: item.id,
                            text: item.nome,
                        };
                    }),
                };
            },
        },
    });
});