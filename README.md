# CovidApp

Esta aplicacion es una prueba de un gestor de incidencias de coronavirus por ciudad o region la aplicacion cuenta con unos datos precargados:

[
	{
		"nameCountry": "Spain",
		"regions": [
			{
				"nameRegion": "Andalucia",
				"cities":[
					{
						"nameCity": "Malaga",
						"npopulation": 577405,
						 "incidences": [
                            {
                                "nincidence": 300,
                                "fecha": "2021-12-11T00:00:00.000+00:00"
                            },
                            {
                                "nincidence": 150,
                                "fecha": "2021-12-12T00:00:00.000+00:00"
                            },
                            {
                                "nincidence": 100,
                                "fecha": "2021-12-13T00:00:00.000+00:00"
                            },
                            {
                                "nincidence": 50,
                                "fecha": "2021-12-14T00:00:00.000+00:00"
                            },
                            {
                                "nincidence": 30,
                                "fecha": "2021-12-15T00:00:00.000+00:00"
                            },
                            {
                                "nincidence": 10,
                                "fecha": "2021-12-16T00:00:00.000+00:00"
                            }
                        ]
					},
					{
						"nameCity": "Sevilla",
						"npopulation": 684234
					}
				]
			},
			{
				"nameRegion": "Castilla y Leon",
				"cities":[
					{
						"nameCity": "Leon",
						"npopulation": 122051
					},
					{
						"nameCity": "Zamora",
						"npopulation": 60988
					},
					{
						"nameCity": "Salamanca",
						"npopulation": 143269
					}
				]
			},
			{
				"nameRegion": "Cantalunya",
				"cities":[
					{
						"nameCity": "Barcelona",
						"npopulation": 1660314
					},
					{
						"nameCity": "Gerona",
						"npopulation": 101932
					},
					{
						"nameCity": "Tarragona",
						"npopulation": 135426
					},
					{
						"nameCity": "Lerida",
						"npopulation": 140080
					}
				]
			}
		]
	},
	{
		"nameCountry": "Germany",
		"regions": [
			{
				"nameRegion": "Baviera",
				"cities":[
					{
						"nameCity": "Munich",
						"npopulation": 1561094
					},
					{
						"nameCity": "Nuremberg",
						"npopulation": 518365
					}
				]
			}
		]
	}
]

Con estos datos se levantara la aplicacion, aunque se podran añadir nuevos utilizando los servicios creados.


Respuesta pregunta:

Dado que el servicio web para la consulta de tasa de incidencia de coronavirus
hay previsión que lo usarán desde todos los países de Europa, esperamos
cientos de miles de peticiones a lo largo del día y necesitamos que las
consultas sean muy rápidas (<400 ms por request/response), explicar (no hay
que implementar) como se podría cumplir con este objetivo.

habria que aumentar el numero de contenedores disponibles (PODs) en cada una de las regiones de europa que nos permita la plataforma cloud que usemos.
