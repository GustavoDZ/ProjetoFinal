var fragmentoPerfil = '<h3> {{NOME}} </h3>' + 
                      '<hr>'+
                      'RACF: {{RACF}} <br>' +
                      'Email: {{EMAIL}} <br>' +
                      'Setor: {{SETOR}} ' +
                      '<hr>';
var fragmentoFoto   = '<img src="{{LINKFOTO}}" width="100%">';
var fragmentoPedido = '<div class="col-12">'+
                         ' {{DATAPEDIDO}} {{NUMPEDIDO}} {{OBSERVACOES}}'+
                      '</div>';


function novoPedido(){
    window.location = "novopedido.html";
}
// este metodo eh um dos mais trabalhosos, pois ele pega a informacao do usuario
// e tem que preencher praticamente a pagina toda.
function carregaUser(){
    var userStr = localStorage.getItem("user");
    if (!userStr){  // se nao tiver isso no localStorage, redireciona para o index (login)
        window.location = "Login.html";
    }
    else{

        // se o usuario existe armazenado, eu pego, converto-o para JSON
        var user = JSON.parse(userStr);
        // e comeco a preencher as diferentes secoes da minha pagina
        // secao do perfil
        var strPerfil = fragmentoPerfil.replace("{{NOME}}",user.nome)
                                       .replace("{{EMAIL}}",user.email)
                                       .replace("{{RACF}}",user.racf)
                                       .replace("{{SETOR}}",user.setor);
        document.getElementById("perfil").innerHTML = strPerfil;

        // secao da foto
        document.getElementById("fotoUser").innerHTML = 
            fragmentoFoto.replace("{{LINKFOTO}}",user.linkFoto);

        // secao dos pedidos
        var strPedidos="";
        //console.log(user.pedidos.length);
        //if(user.pedidos.length() > 1){
            for (i=0; i<user.pedidos.length; i++){
                let pedidoatual = fragmentoPedido;
                strPedidos += pedidoatual.replace("{{DATAPEDIDO}}",user.pedidos[i].dataPedido)
                                        .replace("{{NUMPEDIDO}}",user.pedidos[i].numPedido)   
                                        .replace("{{OBSERVACOES}}",user.pedidos[i].observacoes);
            }
            document.getElementById("pedidos").innerHTML = strPedidos;
        //}
    }
}