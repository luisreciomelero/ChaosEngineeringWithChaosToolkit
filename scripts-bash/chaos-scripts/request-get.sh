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
	for ((i = 0 ; i < $1 ; i++));
	do
		echo "peticion"
		curl -H 'Content-Type: application/json' \
			"http://"$INGRESS_HOST"/read/api/usuarios/"
		echo ""
		echo "------------------------------------------------"
	done
	echo "Realizadas todas las peticiones!"
fi