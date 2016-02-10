function actualizar_opciones(d){
	var divImagen = document.querySelectorAll("#divImagenes")[0];
	if(d.value=="Si"){
		removeClass(divImagen,"hidden");
	}else{
		addClass(divImagen,"hidden");
	}
}
function actualiza_opcionFecha(s){
	var fechaFin = document.querySelectorAll("#fechaFin")[0];
	if(s.value=="si"){
		removeClass(fechaFin,"hidden");
	}else{
		addClass(fechaFin,"hidden");
	}
}
function removeClass(obj,cls){
	var miExpReg = /./;
    var exp =new RegExp('(\\s|^)'+cls+'(\\s|$)');
    obj.className=obj.className.replace(exp,"");
}
function addClass(obj,cls){
    obj.className+=" "+cls;
}