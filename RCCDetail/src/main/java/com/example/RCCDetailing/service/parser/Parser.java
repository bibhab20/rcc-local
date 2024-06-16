package com.example.RCCDetailing.service.parser;

import com.example.RCCDetailing.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser extends AbstractParser{
    @Override
    public AbstractStructure getStructure(String text) {
        AbstractStructure structure = new Structure();
        List<String> sections = getSegmentSections(text);
        SimpleSegmentParser segmentParser = new SimpleSegmentParser();
        List<Segment> segments = new ArrayList<>();
        //incidence parsing
        List<List<String>> jointCoordinateAndMemberIncidenceLines = getIncidenceAndMemberLines(text);
        List<String> jointCoordinateLines = jointCoordinateAndMemberIncidenceLines.get(0);
        List<String> memberIncidenceLines = jointCoordinateAndMemberIncidenceLines.get(1);


        NodeParser nodeParser = new NodeParser();
        List<Node> nodes = nodeParser.parseNodes(jointCoordinateLines);


        IncidenceParser incidenceParser = new IncidenceParser();
        Map<Integer, Incidence> incidenceMap = incidenceParser.parseIncidence(memberIncidenceLines, nodes);



        //mapping incidence to structure


        for(String section: sections){
            segments.add(segmentParser.parse(section));
        }
        for(Segment segment: segments){
            if(segment!=null)
                structure.addSegment(segment);
        }
        mapIncidentToStructure(structure, incidenceMap);
        return structure;

    }

    private List<String> getSegmentSections(String text){
        StringBuilder sb = new StringBuilder();
        sb.append("==".repeat(38));
        String[] arr = text.split(sb.toString());
        return cleanUp(arr);

    }
    private List<String> cleanUp(String[] arr){
        List<String> ans = new ArrayList<>();
        for(String s: arr){
            s = s.trim();
            if(s.contains("L I M I T    S T A T E    D E S I G N")){
                ans.add(s);
            }
        }
        return ans;
    }

    private List<List<String>> getIncidenceAndMemberLines(String text){
        List<String> jointCoordinateLines = new ArrayList<>();
        List<String> memberIncidenceLines = new ArrayList<>();
        String[] ar = text.split("\n");
        int startIndex =10;
        while(startIndex<ar.length && !ar[startIndex].contains("JOINT COORDINATES")){
            startIndex++;
        }
        startIndex++;
        while(startIndex < ar.length && !ar[startIndex].contains("MEMBER")){

            String line = ar[startIndex].trim();
            if(!line.isEmpty() && Character.isDigit(line.charAt(0))){
                jointCoordinateLines.add(line);
            }
            startIndex++;
        }
        System.out.println(ar[startIndex]);
        startIndex++;
        while(startIndex <ar.length){
            String line = ar[startIndex].trim();
            if(line.isEmpty() || line.contains("STAAD")){
                startIndex++;
                continue;
            }
            String[] tempArr = line.split("\\s+");
            if(tempArr.length < 2 || !Character.isDigit(tempArr[1].charAt(0))){
                break;
            }
            memberIncidenceLines.add(line);
            startIndex++;
        }
        List<List<String>> ans = new ArrayList<>();
        ans.add(jointCoordinateLines);
        ans.add(memberIncidenceLines);
        return ans;
    }

    private void mapIncidentToStructure(AbstractStructure structure, Map<Integer, Incidence> incidenceMap){
        List<Beam> beams = structure.getBeams();
        List<Column> columns = structure.getColumns();

        for(Beam beam : beams){
            Incidence incidence = incidenceMap.get(beam.getSegmentNumber());
            beam.setIncidence(incidence);
        }
        for(Column column : columns){
            Incidence incidence = incidenceMap.get(column.getSegmentNumber());
            column.setIncidence(incidence);
        }
    }
}
