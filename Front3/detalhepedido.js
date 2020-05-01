function carregaPedido(){
    var str = window.location.search;
    // ?num=15
    var numPedido = str.substr(5);
    console.log("Capturei NUMPEDIDO = "+numPedido);

    fetch("http://localhost:8080/pedido/"+numPedido)
      .then(res => res.json())
      .then(res => {
          var resultado ="";
          resultado = "Pedido: "+res.numPedido+"<br>"+
                      "Data:" +res.dataPedido+"<br>"+
                      "Solicitante: "+res.solicitante.nome    + "<br>"+
                      "Status: "+res.status;
          document.getElementById("detalhes").innerHTML = resultado;              
 
      })
      .catch(erro => alert("erro ao recuperar!"));
}
function logout(){
    localStorage.removeItem("user");
    window.location = "login.html";
}