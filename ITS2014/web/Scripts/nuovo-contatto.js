$(function() {
    $("#invia").click(function() {
        var nuovo_contatto = {
            "cognome": $("#cognome").val()
            , "nome": $("#nome").val()
            , "cellulare": $("#cellulare").val()
            , "email": $("#email").val()
        };

        $.post(//la post e' il "contrario" della get, ed invio qualcosa al server nel corpo della richiesta http
                "ContattiSQL"
                , JSON.stringify(nuovo_contatto)
                , function(result) { //la pagina rimarra' uguale, ammenoche' non specificato all'interno di questa funzione
                    window.location.href = "Contatti.html"; //in questo caso al termine dell'operazione si rimanderà a Contatti
                }
        );
    });


});