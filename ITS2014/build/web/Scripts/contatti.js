//Tutto ciò che inizia con $ è jquery, il cui parametro ( in questo caso) è una funzione che accetta come parametro una funzione
//Questa è il punto di partenza della pagina, in quanto è la prima cosa che viene eseguita quando la pagina è "pronta"
$(function() {
    reload();
});


function reload() {
    $.getJSON(//funzione di jQuery che accetta due parametri: 
            'ContattiSQL' //url da cui fare il download e 
            , function(result) { //una seconda funzione che darà come risultato il risultato del download

                var tabella_contatti = $("#contatti > tbody");
                tabella_contatti.empty();

                $(result).each(function(i, item) {//applica jQuery all'array JSON; each è una funzione jQuery che lo fa iterare ogni volta chiamando jQuery dove i è il numero e item l'elemento/riga
                    var tr_html =
                            "<tr data-contatto-id ='" + item.contatto_id + "'>" //stiamo componendo il template della parte da comporre
                            + "<td>" + item.cognome + "</td>"
                            + "<td>" + item.nome + "</td>"
                            + "<td>" + item.cellulare + "</td>"
                            + "<td>" + item.email + "</td>"
                            + "<td>"
                            + "<a href='ModificaContatto.jsp?ContattoId="
                            + item.contatto_id
                            + "'>modifica</a> "
                            + "</td>"
                            + "<td><a class='cancella' href='#'>cancella</a></td>"
                            + "</tr>";
                    tabella_contatti.append(tr_html);
                });

                //cancella
                $(".cancella").click(function(e) {
                    if (confirm("Sei sicuro?") == false)
                        return;

                    var contatto_id =
                            $(e.target) //a
                            .parents('tr') //td + tr
                            .attr("data-contatto-id");

                    $.ajax({
                        url: "ContattiSQL?ContattoId=" + contatto_id
                        , type: "DELETE"
                        , data: JSON.stringify({})
                        , success: function(result) { //la pagina rimarra' uguale, ammenoche' non specificato all'interno di questa funzione
                            reload(); //in questo caso al termine dell'operazione si rimanderà a Contatti
                        }
                    });
                });
            }
    );//la comunicazione è di tipo asincrona (~quando facciamo la richiesta l'applicazione NON si ferma)

}