$(document).ready(function() {
  $('#autorId').select2({
      ajax: {
          url: '/api/autores',
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
      ajax: {
          url: '/api/editoras',
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