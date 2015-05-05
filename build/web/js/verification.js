$(function()
{
    
    $('#form-liste').validate({
   
        rules: {
            club: {
                required: function()
                {
                    return $('#select-equipe').val() !== null && $('#select-equipe').val() !== "";
                }
                
            }
        },
        
        messages: {
            club: {
                required: 'Le club est obligatoire'
            }
        }

    });
    
    $('#form-ident').validate({
        rules: {
            club: { required: true, number:true },
            password: { required: true }
            
            
        },
        
        messages: {
            club: {
                required: 'Identifiant obligatoire',
                number: 'Identifiant est un nombre'
            },
            password: {
                required: 'Mot de passe obligatoire'
            }
        }
    });
    
    // A finir
    $('#form-encodage').validate({
       rules: {
           'club-home': {
               required: true,
               number: true,
               min: 0
           },
           'club-visiteur': {
               required: true,
               number: true,
               min: 0
           },
           'equipe-home': {
               required: true,
               number: true,
               min: 0
           },
           'equipe-visiteur': {
               required: true,
               number: true,
               min: 0
           },
           'score-home': {
               required: true,
               number: true,
               min: 0
           },
           'score-visiteur': {
               required: true,
               number: true,
               min: 0
           },
           journee: {
               required:true,
               number: true,
               min: 0
           },
           datePrevue: {
               required: true
           },
           dateReelle: {
               required: true
           }
       },
       messages: {
           'club-home': {
               required: 'Requis',
               number: 'Requis',
               min: 'Requis'
           },
           'club-visiteur': {
               required: 'Requis',
               number: 'Requis',
               min: 'Requis'
           },
           'equipe-home': {
               required: 'Requis',
               number: 'Requis',
               min: 'Requis'
           },
           'equipe-visiteur': {
               required: 'Requis',
               number: 'Requis',
               min: 'Requis'
           },
           'score-home': {
               required: 'Requis',
               number: 'Nombre requis',
               min: 'Nombre positif requis'
           },
           'score-visiteur': {
               required: 'Requis',
               number: 'Nombre requis',
               min: 'Nombre positif requis'
           },
           journee: {
               required: 'Requis',
               number: 'Nombre requis',
               min: 'Nombre positif requis'
           },
           datePrevue: {
               required: 'Requis'
           },
           dateReelle: {
               required: 'Requis'
           }
           
       }
    });
});