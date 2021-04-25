#!/bin/bash

export INGRESS_PORT=$(kubectl \
    --namespace istio-system \
    get service istio-ingressgateway \
   --output jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')

#export INGRESS_HOST=$(minikube ip):$INGRESS_PORT
#export INGRESS_PORT=$(kubectl--namespace istio-system -o jsonpath="{.spec.ports[1].nodePort}" services istio-ingressgateway)

export INGRESS_HOST=$(kubectl get nodes --namespace istio-system -o jsonpath="{.items[0].status.addresses[0].address}"):$INGRESS_PORT

echo "URL: ${INGRESS_HOST}"

help() {
  echo "El primer argumento  "
  exit 1
}
es_numero='^[0-9]+$'

if [ $# -ne 2 ] || ! [[ $1 =~ $es_numero ]] || ! [[ $2 =~ $es_numero ]] ; then
  help 

else
  echo "Se realizarán $2 rafagas concurrentes de $1 peticiones "

  NUM_UUID=$1*$2

  my_array=()
	for ((i = 0 ; i < $NUM_UUID ; i++));
	do
		NEW_UUID=$(cat /dev/urandom | tr -dc '0-9-A-Z' | fold -w 9 | head -n 1)
		my_array+=($NEW_UUID)
		
	done
	echo "${my_array[@]}"
  
  siege -r $1 -c $2 -v "$INGRESS_HOST/mg/api/usuarios/ POST " --content-type "application/json" 

  echo "Realizadas todas las peticiones!"
fi