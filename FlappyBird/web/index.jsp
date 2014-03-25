<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8" />
        <title>Flappy Bird Clone</title>
        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.css"/> <!--Caricamento fogli di stile-->
        <link rel="stylesheet" type="text/css" href="CSS/style.css"/>
        <style>
            #game_div, p {
                width: 400px;
                margin: auto;
                margin-top: 20px;
            }
        </style>
    </head>

    <body>
        <div class='col-md-offset-3'>
            <form>
                <fieldset class='form-control'>
                    <label for="username">Username</label>
                    <input type="text" id="username" />
                    <a href="#" id="invia">Inizia</a>
                </fieldset>
            </form>
        </div>
        
        <div class='container'>
        <p>Press the spacebar to jump</p>
        
        <div id="game_div" class='col-md-6'> </div> <!--div in cui si svolge il gioco-->
        <br />
        <div class='col-md-6'> <!--div in cui viene presentata la tabella-->
            <table id='scores' class='col-md-offset-3 table table-bordered'> <!--di nome 'scores' in cui si vede la top5-->
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Score</th>
                        <th>Timestamp</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        </div>
        <script type="text/javascript" src="Scripts/jquery-1.11.0.js"></script>
        <script type="text/javascript" src="Scripts/phaser.min.js"></script>
        <script type="text/javascript" src="Scripts/main.js"></script>

    </body>

</html>
