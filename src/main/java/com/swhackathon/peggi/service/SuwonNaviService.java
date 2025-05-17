package com.swhackathon.peggi.service;

import com.swhackathon.peggi.dto.CollegeResponse;
import com.swhackathon.peggi.dto.ErrorResponse;
import com.swhackathon.peggi.dto.SuwonNaviResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SuwonNaviService {

    public CollegeResponse getColleges() {
        List<String> buildings = Arrays.asList(
                "mainGate", "humanitiesCollege", "gym", "futureInnovationCenter",
                "innovationEngineeringCollege", "advancedScienceInstitute", "engineering1", "engineering2",
                "engineering3", "engineering4", "sculptureHall", "designArtCollege", "aceCenter",
                "swFusionCollege", "lifeCareScienceCollege", "socialCollege", "musicCollege",
                "amarensHall", "dormitory", "globalManagement", "globalTalentCollege", "administrationBuilding",
                "backGate", "centralLibrary", "unionHall"
        );

        return new CollegeResponse(buildings);
    }

    private final Map<String, Map<String, Integer>> graph = new HashMap<>();

    {
        addEdge("mainGate", "humanitiesCollege", 5);
        addEdge("mainGate", "gym", 5);
        addEdge("mainGate", "futureInnovationCenter", 8);
        addEdge("mainGate", "innovationEngineeringCollege", 10);
        addEdge("mainGate", "advancedScienceInstitute", 12);
        addEdge("mainGate", "engineering1", 13);
        addEdge("mainGate", "engineering2", 14);
        addEdge("mainGate", "engineering3", 15);
        addEdge("mainGate", "engineering4", 17);
        addEdge("mainGate", "sculptureHall", 16);
        addEdge("mainGate", "designArtCollege", 15);
        addEdge("mainGate", "aceCenter", 13);
        addEdge("mainGate", "swFusionCollege", 15);
        addEdge("mainGate", "lifeCareScienceCollege", 15);
        addEdge("mainGate", "socialCollege", 18);
        addEdge("mainGate", "musicCollege", 19);
        addEdge("mainGate", "amarensHall", 21);
        addEdge("mainGate", "dormitory", 24);
        addEdge("mainGate", "globalManagement", 25);
        addEdge("mainGate", "globalTalentCollege", 17);
        addEdge("mainGate", "administrationBuilding", 16);
        addEdge("mainGate", "backGate", 18);

        addEdge("humanitiesCollege", "gym", 2);
        addEdge("humanitiesCollege", "futureInnovationCenter", 5);
        addEdge("humanitiesCollege", "innovationEngineeringCollege", 7);
        addEdge("humanitiesCollege", "advancedScienceInstitute", 9);
        addEdge("humanitiesCollege", "engineering1", 10);
        addEdge("humanitiesCollege", "engineering2", 11);
        addEdge("humanitiesCollege", "engineering3", 12);
        addEdge("humanitiesCollege", "engineering4", 14);
        addEdge("humanitiesCollege", "sculptureHall", 14);
        addEdge("humanitiesCollege", "designArtCollege", 13);
        addEdge("humanitiesCollege", "aceCenter", 10);
        addEdge("humanitiesCollege", "swFusionCollege", 14);
        addEdge("humanitiesCollege", "lifeCareScienceCollege", 14);
        addEdge("humanitiesCollege", "socialCollege", 17);
        addEdge("humanitiesCollege", "musicCollege", 18);
        addEdge("humanitiesCollege", "amarensHall", 20);
        addEdge("humanitiesCollege", "dormitory", 23);
        addEdge("humanitiesCollege", "globalManagement", 24);
        addEdge("humanitiesCollege", "globalTalentCollege", 16);
        addEdge("humanitiesCollege", "administrationBuilding", 15);
        addEdge("humanitiesCollege", "backGate", 15);

        addEdge("gym", "futureInnovationCenter", 3);
        addEdge("gym", "innovationEngineeringCollege", 5);
        addEdge("gym", "advancedScienceInstitute", 7);
        addEdge("gym", "engineering1", 8);
        addEdge("gym", "engineering2", 9);
        addEdge("gym", "engineering3", 10);
        addEdge("gym", "engineering4", 12);
        addEdge("gym", "sculptureHall", 12);
        addEdge("gym", "designArtCollege", 11);
        addEdge("gym", "aceCenter", 9);
        addEdge("gym", "swFusionCollege", 14);
        addEdge("gym", "lifeCareScienceCollege", 14);
        addEdge("gym", "socialCollege", 17);
        addEdge("gym", "musicCollege", 18);
        addEdge("gym", "amarensHall", 20);
        addEdge("gym", "dormitory", 23);
        addEdge("gym", "globalManagement", 24);
        addEdge("gym", "globalTalentCollege", 16);
        addEdge("gym", "administrationBuilding", 15);
        addEdge("gym", "backGate", 13);

        addEdge("futureInnovationCenter", "innovationEngineeringCollege", 2);
        addEdge("futureInnovationCenter", "advancedScienceInstitute", 3);
        addEdge("futureInnovationCenter", "engineering1", 4);
        addEdge("futureInnovationCenter", "engineering2", 6);
        addEdge("futureInnovationCenter", "engineering3", 7);
        addEdge("futureInnovationCenter", "engineering4", 9);
        addEdge("futureInnovationCenter", "sculptureHall", 9);
        addEdge("futureInnovationCenter", "designArtCollege", 7);
        addEdge("futureInnovationCenter", "aceCenter", 5);
        addEdge("futureInnovationCenter", "swFusionCollege", 11);
        addEdge("futureInnovationCenter", "lifeCareScienceCollege", 11);
        addEdge("futureInnovationCenter", "socialCollege", 14);
        addEdge("futureInnovationCenter", "musicCollege", 15);
        addEdge("futureInnovationCenter", "amarensHall", 17);
        addEdge("futureInnovationCenter", "dormitory", 20);
        addEdge("futureInnovationCenter", "globalManagement", 21);
        addEdge("futureInnovationCenter", "globalTalentCollege", 13);
        addEdge("futureInnovationCenter", "administrationBuilding", 12);
        addEdge("futureInnovationCenter", "backGate", 10);

        addEdge("innovationEngineeringCollege", "advancedScienceInstitute", 1);
        addEdge("innovationEngineeringCollege", "engineering1", 2);
        addEdge("innovationEngineeringCollege", "engineering2", 3);
        addEdge("innovationEngineeringCollege", "engineering3", 4);
        addEdge("innovationEngineeringCollege", "engineering4", 6);
        addEdge("innovationEngineeringCollege", "sculptureHall", 6);
        addEdge("innovationEngineeringCollege", "designArtCollege", 5);
        addEdge("innovationEngineeringCollege", "aceCenter", 3);
        addEdge("innovationEngineeringCollege", "swFusionCollege", 9);
        addEdge("innovationEngineeringCollege", "lifeCareScienceCollege", 9);
        addEdge("innovationEngineeringCollege", "socialCollege", 12);
        addEdge("innovationEngineeringCollege", "musicCollege", 13);
        addEdge("innovationEngineeringCollege", "amarensHall", 15);
        addEdge("innovationEngineeringCollege", "dormitory", 18);
        addEdge("innovationEngineeringCollege", "globalManagement", 19);
        addEdge("innovationEngineeringCollege", "globalTalentCollege", 11);
        addEdge("innovationEngineeringCollege", "administrationBuilding", 10);
        addEdge("innovationEngineeringCollege", "backGate", 8);

        addEdge("advancedScienceInstitute", "engineering1", 1);
        addEdge("advancedScienceInstitute", "engineering2", 3);
        addEdge("advancedScienceInstitute", "engineering3", 4);
        addEdge("advancedScienceInstitute", "engineering4", 6);
        addEdge("advancedScienceInstitute", "sculptureHall", 6);
        addEdge("advancedScienceInstitute", "designArtCollege", 5);
        addEdge("advancedScienceInstitute", "aceCenter", 3);
        addEdge("advancedScienceInstitute", "swFusionCollege", 9);
        addEdge("advancedScienceInstitute", "lifeCareScienceCollege", 9);
        addEdge("advancedScienceInstitute", "socialCollege", 12);
        addEdge("advancedScienceInstitute", "musicCollege", 13);
        addEdge("advancedScienceInstitute", "amarensHall", 15);
        addEdge("advancedScienceInstitute", "dormitory", 18);
        addEdge("advancedScienceInstitute", "globalManagement", 19);
        addEdge("advancedScienceInstitute", "globalTalentCollege", 11);
        addEdge("advancedScienceInstitute", "administrationBuilding", 10);
        addEdge("advancedScienceInstitute", "backGate", 7);

        addEdge("engineering1", "engineering2", 2);
        addEdge("engineering1", "engineering3", 3);
        addEdge("engineering1", "engineering4", 5);
        addEdge("engineering1", "sculptureHall", 7);
        addEdge("engineering1", "designArtCollege", 6);
        addEdge("engineering1", "aceCenter", 4);
        addEdge("engineering1", "swFusionCollege", 10);
        addEdge("engineering1", "lifeCareScienceCollege", 10);
        addEdge("engineering1", "socialCollege", 12);
        addEdge("engineering1", "musicCollege", 13);
        addEdge("engineering1", "amarensHall", 16);
        addEdge("engineering1", "dormitory", 19);
        addEdge("engineering1", "globalManagement", 20);
        addEdge("engineering1", "globalTalentCollege", 12);
        addEdge("engineering1", "administrationBuilding", 11);
        addEdge("engineering1", "backGate", 6);

        addEdge("engineering2", "engineering3", 1);
        addEdge("engineering2", "engineering4", 3);
        addEdge("engineering2", "sculptureHall", 6);
        addEdge("engineering2", "designArtCollege", 7);
        addEdge("engineering2", "aceCenter", 5);
        addEdge("engineering2", "swFusionCollege", 9);
        addEdge("engineering2", "lifeCareScienceCollege", 11);
        addEdge("engineering2", "socialCollege", 13);
        addEdge("engineering2", "musicCollege", 14);
        addEdge("engineering2", "amarensHall", 17);
        addEdge("engineering2", "dormitory", 20);
        addEdge("engineering2", "globalManagement", 21);
        addEdge("engineering2", "globalTalentCollege", 13);
        addEdge("engineering2", "administrationBuilding", 12);
        addEdge("engineering2", "backGate", 4);

        addEdge("engineering3", "engineering4", 2);
        addEdge("engineering3", "sculptureHall", 6);
        addEdge("engineering3", "designArtCollege", 7);
        addEdge("engineering3", "aceCenter", 5);
        addEdge("engineering3", "swFusionCollege", 10);
        addEdge("engineering3", "lifeCareScienceCollege", 12);
        addEdge("engineering3", "socialCollege", 14);
        addEdge("engineering3", "musicCollege", 15);
        addEdge("engineering3", "amarensHall", 18);
        addEdge("engineering3", "dormitory", 21);
        addEdge("engineering3", "globalManagement", 22);
        addEdge("engineering3", "globalTalentCollege", 14);
        addEdge("engineering3", "administrationBuilding", 13);
        addEdge("engineering3", "backGate", 3);

        addEdge("engineering4", "sculptureHall", 3);
        addEdge("engineering4", "designArtCollege", 5);
        addEdge("engineering4", "aceCenter", 7);
        addEdge("engineering4", "swFusionCollege", 12);
        addEdge("engineering4", "lifeCareScienceCollege", 14);
        addEdge("engineering4", "socialCollege", 16);
        addEdge("engineering4", "musicCollege", 17);
        addEdge("engineering4", "amarensHall", 20);
        addEdge("engineering4", "dormitory", 23);
        addEdge("engineering4", "globalManagement", 24);
        addEdge("engineering4", "globalTalentCollege", 11);
        addEdge("engineering4", "administrationBuilding", 10);
        addEdge("engineering4", "backGate", 1);

        addEdge("sculptureHall", "designArtCollege", 1);
        addEdge("sculptureHall", "aceCenter", 3);
        addEdge("sculptureHall", "swFusionCollege", 10);
        addEdge("sculptureHall", "lifeCareScienceCollege", 11);
        addEdge("sculptureHall", "socialCollege", 13);
        addEdge("sculptureHall", "musicCollege", 15);
        addEdge("sculptureHall", "amarensHall", 17);
        addEdge("sculptureHall", "dormitory", 20);
        addEdge("sculptureHall", "globalManagement", 21);
        addEdge("sculptureHall", "globalTalentCollege", 7);
        addEdge("sculptureHall", "administrationBuilding", 6);
        addEdge("sculptureHall", "backGate", 4);

        addEdge("designArtCollege", "aceCenter", 2);
        addEdge("designArtCollege", "swFusionCollege", 9);
        addEdge("designArtCollege", "lifeCareScienceCollege", 10);
        addEdge("designArtCollege", "socialCollege", 11);
        addEdge("designArtCollege", "musicCollege", 13);
        addEdge("designArtCollege", "amarensHall", 15);
        addEdge("designArtCollege", "dormitory", 18);
        addEdge("designArtCollege", "globalManagement", 19);
        addEdge("designArtCollege", "globalTalentCollege", 6);
        addEdge("designArtCollege", "administrationBuilding", 5);
        addEdge("designArtCollege", "backGate", 6);

        addEdge("aceCenter", "swFusionCollege", 6);
        addEdge("aceCenter", "lifeCareScienceCollege", 8);
        addEdge("aceCenter", "socialCollege", 11);
        addEdge("aceCenter", "musicCollege", 12);
        addEdge("aceCenter", "amarensHall", 14);
        addEdge("aceCenter", "dormitory", 17);
        addEdge("aceCenter", "globalManagement", 18);
        addEdge("aceCenter", "globalTalentCollege", 8);
        addEdge("aceCenter", "administrationBuilding", 7);
        addEdge("aceCenter", "backGate", 8);

        addEdge("swFusionCollege", "lifeCareScienceCollege", 1);
        addEdge("swFusionCollege", "socialCollege", 4);
        addEdge("swFusionCollege", "musicCollege", 5);
        addEdge("swFusionCollege", "amarensHall", 7);
        addEdge("swFusionCollege", "dormitory", 10);
        addEdge("swFusionCollege", "globalManagement", 11);
        addEdge("swFusionCollege", "globalTalentCollege", 10);
        addEdge("swFusionCollege", "administrationBuilding", 9);
        addEdge("swFusionCollege", "backGate", 16);

        addEdge("lifeCareScienceCollege", "socialCollege", 4);
        addEdge("lifeCareScienceCollege", "musicCollege", 5);
        addEdge("lifeCareScienceCollege", "amarensHall", 7);
        addEdge("lifeCareScienceCollege", "dormitory", 10);
        addEdge("lifeCareScienceCollege", "globalManagement", 11);
        addEdge("lifeCareScienceCollege", "globalTalentCollege", 11);
        addEdge("lifeCareScienceCollege", "administrationBuilding", 10);
        addEdge("lifeCareScienceCollege", "backGate", 15);

        addEdge("socialCollege", "musicCollege", 1);
        addEdge("socialCollege", "amarensHall", 4);
        addEdge("socialCollege", "dormitory", 6);
        addEdge("socialCollege", "globalManagement", 7);
        addEdge("socialCollege", "globalTalentCollege", 8);
        addEdge("socialCollege", "administrationBuilding", 7);
        addEdge("socialCollege", "backGate", 15);

        addEdge("musicCollege", "amarensHall", 2);
        addEdge("musicCollege", "dormitory", 5);
        addEdge("musicCollege", "globalManagement", 6);
        addEdge("musicCollege", "globalTalentCollege", 9);
        addEdge("musicCollege", "administrationBuilding", 8);
        addEdge("musicCollege", "backGate", 17);

        addEdge("amarensHall", "dormitory", 2);
        addEdge("amarensHall", "globalManagement", 3);
        addEdge("amarensHall", "globalTalentCollege", 12);
        addEdge("amarensHall", "administrationBuilding", 11);
        addEdge("amarensHall", "backGate", 20);

        addEdge("dormitory", "globalManagement", 1);
        addEdge("dormitory", "globalTalentCollege", 15);
        addEdge("dormitory", "administrationBuilding", 14);
        addEdge("dormitory", "backGate", 23);

        addEdge("globalManagement", "globalTalentCollege", 16);
        addEdge("globalManagement", "administrationBuilding", 15);
        addEdge("globalManagement", "backGate", 24);

        addEdge("globalTalentCollege", "administrationBuilding", 3);
        addEdge("globalTalentCollege", "backGate", 11);

        addEdge("administrationBuilding", "backGate", 10);

        addEdge("centralLibrary", "mainGate", 16);
        addEdge("centralLibrary", "humanitiesCollege", 16);
        addEdge("centralLibrary", "gym", 14);
        addEdge("centralLibrary", "futureInnovationCenter", 14);
        addEdge("centralLibrary", "innovationEngineeringCollege", 12);
        addEdge("centralLibrary", "advancedScienceInstitute", 12);
        addEdge("centralLibrary", "engineering1", 13);
        addEdge("centralLibrary", "engineering2", 14);
        addEdge("centralLibrary", "engineering3", 15);
        addEdge("centralLibrary", "engineering4", 16);
        addEdge("centralLibrary", "sculptureHall", 12);
        addEdge("centralLibrary", "designArtCollege", 8);
        addEdge("centralLibrary", "aceCenter", 10);
        addEdge("centralLibrary", "swFusionCollege", 7);
        addEdge("centralLibrary", "lifeCareScienceCollege", 7);
        addEdge("centralLibrary", "socialCollege", 13);
        addEdge("centralLibrary", "musicCollege", 14);
        addEdge("centralLibrary", "amarensHall", 16);
        addEdge("centralLibrary", "dormitory", 19);
        addEdge("centralLibrary", "globalManagement", 20);
        addEdge("centralLibrary", "globalTalentCollege", 11);
        addEdge("centralLibrary", "administrationBuilding", 10);
        addEdge("centralLibrary", "backGate", 16);
        addEdge("centralLibrary", "unionHall", 9);

        addEdge("unionHall", "mainGate", 10);
        addEdge("unionHall", "humanitiesCollege", 10);
        addEdge("unionHall", "gym", 8);
        addEdge("unionHall", "futureInnovationCenter", 5);
        addEdge("unionHall", "innovationEngineeringCollege", 3);
        addEdge("unionHall", "advancedScienceInstitute", 3);
        addEdge("unionHall", "engineering1", 3);
        addEdge("unionHall", "engineering2", 4);
        addEdge("unionHall", "engineering3", 5);
        addEdge("unionHall", "engineering4", 6);
        addEdge("unionHall", "sculptureHall", 4);
        addEdge("unionHall", "designArtCollege", 3);
        addEdge("unionHall", "aceCenter", 1);
        addEdge("unionHall", "swFusionCollege", 4);
        addEdge("unionHall", "lifeCareScienceCollege", 7);
        addEdge("unionHall", "socialCollege", 10);
        addEdge("unionHall", "musicCollege", 11);
        addEdge("unionHall", "amarensHall", 13);
        addEdge("unionHall", "dormitory", 16);
        addEdge("unionHall", "globalManagement", 17);
        addEdge("unionHall", "globalTalentCollege", 9);
        addEdge("unionHall", "administrationBuilding", 8);
        addEdge("unionHall", "backGate", 9);


    }

    private void addEdge(String from, String to, int time) {
        graph.computeIfAbsent(from, k -> new HashMap<>()).put(to, time);
        graph.computeIfAbsent(to, k -> new HashMap<>()).put(from, time); // 양방향
    }

    public ResponseEntity<Object> calculatePathTime(List<String> buildings) {
        String from = buildings.get(0);
        String to = buildings.get(1);

        Integer time = graph.getOrDefault(from, Map.of()).get(to);

        if (time == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse(from + " → " + to + " 경로를 찾을 수 없습니다."));
        }

        return ResponseEntity.ok(new SuwonNaviResponse(time));
    }
}