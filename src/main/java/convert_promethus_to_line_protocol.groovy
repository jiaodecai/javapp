def processingTags(String tags) {
    tagMap = [:]
    tagsList = tags.split(/,(?=(?:(?:[^"]*"){2})*[^"]*$)/).each {tag ->
       if (!tag.isEmpty()) {        
         key = tag.split("=")[0]
         value = tag.split("=")[1]
         try {
            tagMap[key] = value.replaceAll("\"", "").toDouble()
            println("$value converted")
          } catch(Exception e) {
            tagMap[key] = value.replaceAll(",", "\\\\,").replaceAll(/\s/, "\\\\ ")   
         }
       }
    }
    
    tagMap.collect { entry->
      "$entry.key=$entry.value"
    }.join(",")
}


new File("metrics.out").withWriter { writer ->
    new File("metrics.txt").eachLine {line ->
        def finder = line =~ /(?<measure>.*?)\{(?<tags>.*)\}\s+(?<value>\d+\.*\d*)/
        if (finder.matches()) {
          println("matched: $line")
          writer.writeLine(finder.group("measure") + "," + processingTags(finder.group("tags")) + " " + "value=" + finder.group("value"))
        } else {
         println("ignore $line")
        }
    }
}
