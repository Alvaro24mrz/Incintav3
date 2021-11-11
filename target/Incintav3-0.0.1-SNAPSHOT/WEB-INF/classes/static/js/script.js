/**
 * 
 */

//Ejecutar función en el evento click
document.getElementById("btn_open").addEventListener("click", open_close_menu);

//Declaramos variables
var side_menu = document.getElementById("menu_side");
var btn_open = document.getElementById("btn_open");
var body = document.getElementById("body");

//Evento para mostrar y ocultar menú
function open_close_menu() {
	body.classList.toggle("body_move");
	side_menu.classList.toggle("menu__side_move");
}

//Si el ancho de la página es menor a 760px, ocultará el menú al recargar la página

if (window.innerWidth < 760) {

	body.classList.add("body_move");
	side_menu.classList.add("menu__side_move");
}

//selected
var noSelectContainer = document.getElementById("opciones_menu");

var noSelect = noSelectContainer.getElementsByClassName("noSelect");

for (var i = 0; i < noSelect.length; i++) {
	noSelect[i].addEventListener("click", function() {
	var current = document.getElementsByClassName("selected");
	
	if(current.length > 0 ){
	current[0].className = current[0].className.replace(" selected", "");
		}
	
		this.className += " selected";
	});
}







//Haciendo el menú responsive(adaptable)

window.addEventListener("resize", function() {

	if (window.innerWidth > 760) {

		body.classList.remove("body_move");
		side_menu.classList.remove("menu__side_move");
	}

	if (window.innerWidth < 760) {

		body.classList.add("body_move");
		side_menu.classList.add("menu__side_move");
	}



});
