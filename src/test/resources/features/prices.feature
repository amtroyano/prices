# language: es
Característica: Consulta de precios de productos

  Escenario: Validar los cinco casos de prueba del enunciado
    Dado que se solicita el precio para la cadena 1, producto 35455 y fecha "<fecha>"
    Cuando se realiza la consulta al servicio de precios
    Entonces el código de respuesta es 200
    Y el precio final es <precio_esperado>
    Y la tarifa aplicada es la <tarifa_esperada>

    Ejemplos:
      | fecha               | precio_esperado | tarifa_esperada |
      | 2020-06-14T10:00:00 | "35,50"         | 1               |
      | 2020-06-14T16:00:00 | "25,45"         | 2               |
      | 2020-06-14T21:00:00 | "35,50"         | 1               |
      | 2020-06-15T10:00:00 | "30,50"         | 3               |
      | 2020-06-16T21:00:00 | "38,95"         | 4               |