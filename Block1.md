## Bloque 1:

### TextView 
Mostrar texto en color rojo, primera letra en mayuscula,
Al inicio, texto por defecto.
### TextEdit
Añadir texto a {TextView}
### CheckBox {Limpiar}
### Boton {Añadir/Limpiar}
CheckBox_{Limpiar} = true , limpia el texto
CheckBox_{Limpiar} = false , sobre escribe el texto
### RadioButtons
Los RadioButtons permite cambiar el color {TextView}.

Nota : Para hacer uso en Java el recurso XML que define el color, podemos usar:

    getResources().getColor(R.color.Nome_del_Color).
