/**
 * Confirmação de exclusão de um contato
 * @author Pedro Rhamon
 */

 function confirmar(idcon) {
	 let resposta = confirm("Confirmar a exclusão deste contato?");
	 if(resposta == true) {
		 alert(idcon);
	 }
 }