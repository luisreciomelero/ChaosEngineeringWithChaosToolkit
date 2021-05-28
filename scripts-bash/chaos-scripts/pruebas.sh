#!/bin/bash

export INGRESS_PORT=$(kubectl \
    --namespace istio-system \
    get service istio-ingressgateway \
   --output jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')

export INGRESS_HOST=$(kubectl get nodes --namespace istio-system -o jsonpath="{.items[0].status.addresses[0].address}"):$INGRESS_PORT

EXPERIMENT=""
REPORT=""
NAME=""
PATH_FILE=""
PATH_REPORTS=""
NAME_REPORT=""
PATH_JOURNAL=""
NAME_JOURNAL=""

help() {
  echo "Este script recibe un param: "
  echo "  Flags: "
  echo "  -e, --experiment: proporcionando el path al experimento a ejecutar"
  echo " "
  exit 1
}

get_name(){
	IFS='.'
	read -a strarr <<< "$1"
	NAME=${strarr[0]}
	echo "GET NAME: $NAME"
}

split_experiment_path(){
	experiment=$EXPERIMENT
	IFS='/'
	read -a strarr <<< "$experiment"
	#Count the total words
	# Print each value of the array by using loop
	
	#echo "There are ${#strarr[*]} words in the text."
	NAME="${strarr[${#strarr[*]}-1]}"
	echo "NAME: $NAME"
	for (( n=0; n < ${#strarr[*]}-1; n++))
	do
		if [ $n = 0 ]; then 
			PATH_FILE="${strarr[n]}"
		else
			PATH_FILE="$PATH_FILE/${strarr[n]}"

		fi
		  	
	done
	PATH_FILE="$PATH_FILE"
	PATH_REPORTS="$PATH_FILE/reports"
	PATH_JOURNAL="$PATH_FILE/journals"
	get_name $NAME

}

# Loop through arguments and process them
for arg in "$@"
do
    case $arg in
        --help | -h)
		help
         # Remove --initialize from processing
        ;;
        --experiment=* | -e=*)
        EXPERIMENT="${arg#*=}"
        ;;
        
        *)
        echo "Flag no reconocida, utilice --help"
		exit
        #shift # Remove generic argument from processing
        ;;
    esac
done


if [ $# -ne 1 ]; then
  help 
fi
if [ -f "$EXPERIMENT" ]; then
	echo "Archivo existe"
	split_experiment_path
else
	echo "Archivo no existe"
fi

echo "PATH_FILE: $PATH_FILE"
echo "PATH_REPORTS: $PATH_REPORTS"
echo "PATH_JOURNAL: $PATH_JOURNAL"
echo "NAME: $NAME"

if [ -d "$PATH_JOURNAL" ]; then
	echo "PATH_JOURNAL ENTRAMOS: $PATH_JOURNAL"
else
	echo "NO"
fi