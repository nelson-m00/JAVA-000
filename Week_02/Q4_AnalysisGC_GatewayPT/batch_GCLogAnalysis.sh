#!/bin/bash
for gc_algorithm in "+UseSerialGC" "+UseParallelGC" "+UseConcMarkSweepGC" "+UseG1GC"
    do
        for heap_size in 128m 512m 1g 2g 4g
            do 
                echo "GC_Algorithm_Parameter:${gc_algorithm}; Heap_size: ${heap_size}" >> GClog.txt
                echo "" >> GCLog.txt

                java -Xms${heap_size} -Xmx${heap_size} -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:${gc_algorithm} GCLogAnalysis >> GCLog.txt 2>> GCLog.txt
                
                echo "" >> GCLog.txt
                echo "-------------------------------------------------------------------" >> GCLog.txt
                echo "" >> GCLog.txt
             done
    done

