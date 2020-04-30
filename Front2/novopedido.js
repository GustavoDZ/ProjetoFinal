
function LoadPage(){
    carregaMaquinas();
    carregaSoftwares();
}

function carregaSoftwares(){
    // preciso ver se o usuario ta logado
    var user = localStorage.getItem("user");
    if (!user){
        window.location="Login.html";
    }
    else{
        fetch('http://localhost:8080/softwares/disponiveis')
           .then(res=>res.json())
           .then(res=>popula(res));
    } 
}


function carregaMaquinas(){
    // preciso ver se o usuario ta logado
    var user = localStorage.getItem("user");
    if (!user){
        window.location="Login.html";
    }
    else{
        fetch('http://localhost:8080/maquinas')
           .then(res=>res.json())
           .then(res=>populaM(res));
    } 
}


function popula(lista){
    var strSoftware = "";
    for (i=0; i<lista.length; i++){
        strSoftware += "<input type='checkbox' id="+i+" name='itens' value='"+lista[i].id+"'>"+lista[i].descricao +"<br>";
    }
    document.getElementById("listasw").innerHTML = strSoftware;
}

function populaM(lista){
    var strMaquina = "<select id=selec >";
    for (i=0; i<lista.length; i++){
        strMaquina += "<option value='"+lista[i].id+"'>"+lista[i].descricao +"</option>";
    }
    strMaquina += "</select>"
    document.getElementById("listasM").innerHTML = strMaquina;
}

function confirmar(){
    var userStr = localStorage.getItem("user");
    var user = JSON.parse(userStr);
    var userid = user.id;

    var d = new Date();
    var m = '0' + parseInt(d.getMonth()+1);
    var strdate = d.getDate()+"/"+m+"/"+d.getFullYear();

    var listaItens = document.getElementsByTagName("input");
    var statusPedido = "N";
    var obs = document.getElementById("txtObs").value;

    var index = document.getElementById("selec").selectedIndex;
    var indexF = parseInt(index+1);
   

    //console.log(userid);
    //console.log(index);
    //console.log(obs);
    console.log(strdate);
    console.log(m);

    var z = 0;
    for (i=0; i<listaItens.length-1;i++){
       if(document.getElementById(i).checked){
          z++;
       }
    }
     console.log(z);
     var c = 1;
    var strItem = "";
    for (i=0; i<listaItens.length-1;i++){
       if(document.getElementById(i).checked){
           if(c < z){
                c++;
                strItem +="{itemSoftware:{id:"+(i+1)+"}},";
           }else{
                strItem +="{itemSoftware:{id:"+(i+1)+"}}";
            }
        }
    }
     //console.log(strItem);
    var msgBody = {
        status: statusPedido,
        observacoes: obs,
        dataPedido: strdate,
        solicitante: {id: userid},
        computador:{id:indexF},
        itens: [strItem]
    }
    console.log(msgBody);

    var cabecalho = {
        method: 'POST',
        body: JSON.stringify(msgBody),
        headers: {
                'Content-Type': 'application/json'
        }
    }
     fetch("http://localhost:8080/pedido/novo",cabecalho)
        .then(res => res.json() )  
        .then(res => {                    
            window.alert("SALVOOOU");  
        })
        .catch(err=>{        
            console.log(err);
            window.alert("BAAAAM");
        });
}




