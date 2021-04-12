#!/bin/bash

export INGRESS_PORT=$(kubectl \
    --namespace istio-system \
    get service istio-ingressgateway \
   --output jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')

#export INGRESS_HOST=$(minikube ip):$INGRESS_PORT
#export INGRESS_PORT=$(kubectl--namespace istio-system -o jsonpath="{.spec.ports[1].nodePort}" services istio-ingressgateway)

export INGRESS_HOST=$(kubectl get nodes --namespace istio-system -o jsonpath="{.items[0].status.addresses[0].address}"):$INGRESS_PORT

help() {
	echo "Este script únicamente admite un único argumento numérico. Será el numero de request que realizará "
	exit 1
}
es_numero='^[0-9]+$'

if [ $# -ne 1 ] || ! [[ $1 =~ $es_numero ]]; then
  help 

else
	echo "Se realizarán $1 periciones"
	for ((i = 0 ; i < $1 ; i++));
	do
		curl "http://"$INGRESS_HOST"/mg/health"
		echo ""
		echo "------------------------------------------------"
	done
	echo "Realizadas todas las peticiones!"
fi