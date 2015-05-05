$(function() {
    
    var $clubH   = $('#select-club-home');
    var $clubV   = $('#select-club-visiteur');
    var $equipeH = $('#select-equipe-home');
    var $equipeV = $('#select-equipe-visiteur');
    var $scoreH = $('#score-home');
    var $scoreV = $('#score-visiteur');
    var $journee = $('#journee');
    var $datePrevue = $('#datePrevue');
    var $dateReelle = $('#dateReelle');
    
    $('#save').on('click', function() {
          
        if ($('#form-encodage').valid())
        {
            var rencontre = {

                    clubH: { 
                        numero: $clubH.val(),
                        nom: $clubH.text()
                    },
                    clubV: {
                        numero: $clubV.val(),
                        nom: $clubV.text()
                    },
                    equipeH: $equipeH.val(),
                    equipeV: $equipeV.val(),
                    datePrevue: $datePrevue.val(),
                    dateReelle: $dateReelle.val(),
                    journee: $journee.val(),
                    scoreH: $scoreH.val(),
                    scoreV: $scoreV.val()
            };

            $.ajax({
               type: 'POST',
               url: 'FrontController?cible=encodage',
               data: {rencontre:rencontre},
               success: function() {
                   // Afficher message de succès.
               },
               error: function()
               {
                   // Afficher message d'erreur
               }

           });
        }
    });
});