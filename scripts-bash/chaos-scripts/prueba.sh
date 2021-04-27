#!/bin/bash

chaos run --help
if [ -f $EXPERIMENT ]; then
	echo "Archivo existe"
	split_experiment_path
	#echo "PATH=$PATH"
	#echo "PATH_REPORTS=$PATH/reports"
	#echo "PATH_JOURNAL=$PATH/journals"
	
	if [ -d $PATH_JOURNAL ]; then
		
		NAME_JOURNAL="$PATH_JOURNAL/$NAME.json"
		

		chaos run $EXPERIMENT --journal-path "$NAME_JOURNAL"

		if [ -d $PATH_REPORTS ] && [ -f $NAME_JOURNAL ]; then
			echo "Los resultados del experimento se han guardado en $NAME_JOURNAL"
			echo "Se ha reconocido el directorio $PATH_REPORTS"
			NAME_REPORT="$PATH_REPORTS/$NAME.pdf"
			echo "Se procede a generar el reporte."
			docker container run --user $(id -u) --volume $PWD:/tmp/result -it \
			chaostoolkit/reporting -- report --export-format=pdf $NAME_JOURNAL $NAME_REPORT
			if [ -f $NAME_REPORT ]; then
				echo "Se ha generado con exito el reporte: $NAME_REPORT"
			else
				echo "No ha sido posible generar el reporte"
			fi
		fi
	fi
	

	
else
	echo "Archivo no existe"
fi
