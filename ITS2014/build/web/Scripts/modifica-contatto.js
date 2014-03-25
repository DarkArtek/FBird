$(function() {

    var contatto_id = $("#contatto_id").val();//in questo modo quando si carica la pagina si vedranno già le informazioni
    $.getJSON(
            "ContattiSQL?ContattoId=" + contatto_id
            , function(contattoViewModel) {
                $("#cognome").val(contattoViewModel.cognome);
                $("#nome").val(contattoViewModel.nome);
                $("#cellulare").val(contattoViewModel.cellulare);
                $("#email").val(contattoViewModel.email);
            }
    );


    $("#invia").click(function() {

        var contatto_esistente = {
            "cognome": $("#cognome").val()
            , "nome": $("#nome").val()
            , "cellulare": $("#cellulare").val()
            , "email": $("#email").val()
        };

        $.ajax({
            url: "ContattiSQL?ContattoId=" + contatto_id
            , type: "PUT"
            , data: JSON.stringify(contatto_esistente)
            , success: function(result) { //la pagina rimarra' uguale, ammenoche' non specificato all'interno di questa funzione
                window.location.href = "Contatti.html"; //in questo caso al termine dell'operazione si rimanderà a Contatti
            }
        });

    });


});