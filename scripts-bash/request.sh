#!/bin/bash

export INGRESS_PORT=$(kubectl \
    --namespace istio-system \
    get service istio-ingressgateway \
    --output jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')

export INGRESS_HOST=$(minikube ip):$INGRESS_PORT

help() {
	echo "Este script únicamente admite un único argumento numérico. Será el numero de request que realizará "
	exit 1
}
es_numero='^[0-9]+$'

if [ $# -ne 1 ] || ! [[ $1 =~ $es_numero ]]; then
  help 

else
	echo "Se realizarán $1 periciones"
	for r in {0..$1}
	do
		NEW_UUID=$(cat /dev/urandom | tr -dc '0-9-A-Z' | fold -w 9 | head -n 1)

		curl -X POST \
			-H 'Content-Type: application/json' \
			-d '{
		    "DNI":"'${NEW_UUID}'",
		    "nombre":"Luis",
		    "apellido":"Recio",
		    "telefono":"600364231",
		    "email":"tfm.muirst.lrm@gmail.com",
		    "password":"12345",
		    "canalPrefer":"email",
		    "canalContac":"email",
		    "estadoNotif":" "
			}'\
			"http://"$INGRESS_HOST"/mg/api/usuarios/"
	done
	echo "Realizadas todas las peticiones!"
fi