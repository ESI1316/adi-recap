$(function() {
    
    var $rencontres = $('#rencontres');
    var $equipeH = $('#id-home');
    var $equipeV = $('#id-visiteur');
    var $scoreH = $('#score-home');
    var $scoreV = $('#score-visiteur');
    var $journee = $('#journee');
    
    var rencontresTab = [];
    var nbRencontres = 0;
    
    // PAS FINI
    $('#add-rencontre').on('click', function() 
    {
        if ($('#form-encodage').valid())
        {
        
            var uneRencontre = {

                equipeH: { 
                    numero: $equipeH.val()
                },
                equipeV: {
                    numero: $equipeV.val()
                },
                datePrevue: {

                },
                dateReelle: {

                },
                journee: $journee.val(),
                scoreH: $scoreH.val(),
                scoreV: $scoreV.val()
            };


            rencontresTab[nbRencontres] = uneRencontre;
            addRencontre(rencontresTab[nbRencontres]);
            nbRencontres++;
        
        }
    });
    
    $('#add-all').on('click', function() {
       
       alert(JSON.stringify(rencontresTab));
       
       $.ajax({
           type: 'POST',
           url: 'FrontController?cible=encodage',
           data: {rencontres:JSON.stringify(rencontresTab)},
           success: clearRencontres,
           error: function()
           {
               alert('Erreur !');
           }
           
       });
    });
    
    $('#cancel').on('click', clearRencontres);
    
    function clearRencontres()
    {
        $rencontres.empty();
        rencontresTab = new Array();
        nbRencontres = 0;
    }
    
    function addRencontre(r)
    {
        $rencontres.append('<li class="rencontre"> Equipe HOME : ' + r.equipeH + ' - Score HOME : ' 
                            + r.scoreH + '    - Score VISITEUR :  '
                            + r.scoreV + ' - Equipe Visiteur : ' + r.equipeV);
    }
    
  
});