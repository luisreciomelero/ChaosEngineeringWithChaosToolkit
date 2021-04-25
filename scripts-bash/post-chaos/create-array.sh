
help() {
	echo "Este script únicamente admite un único argumento numérico. Será el numero de request que realizará "
	exit 1
}
es_numero='^[0-9]+$'


if [ $# -ne 1 ] || ! [[ $1 =~ $es_numero ]]; then
  help 

else
	#NEW_UUID=$(cat /dev/urandom | tr -dc '0-9-A-Z' | fold -w 9 | head -n 1)
	my_array=()
	for ((i = 0 ; i < $1 ; i++));
	do
		NEW_UUID=$(cat /dev/urandom | tr -dc '0-9-A-Z' | fold -w 9 | head -n 1)
		my_array+=($NEW_UUID)
		
	done
	echo "${my_array[@]}"
fi

