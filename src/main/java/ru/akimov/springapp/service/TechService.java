package ru.akimov.springapp.service;

import org.springframework.stereotype.Service;
import ru.akimov.springapp.persistence.domain.tech.*;
import ru.akimov.springapp.persistence.repository.tech.*;

import java.util.List;

@Service
public class TechService {

    private final TechCarrierWallRepository techCarrierWallRepository;
    private final TechExteriorRepository techExteriorRepository;
    private final TechFoundationRepository techFoundationRepository;
    private final TechInteriorRepository techInteriorRepository;
    private final TechLadderRepository techLadderRepository;
    private final TechOverlapsRepository techOverlapsRepository;
    private final TechRoofRepository techRoofRepository;

    public TechService(TechCarrierWallRepository techCarrierWallRepository,
                       TechExteriorRepository techExteriorRepository,
                       TechFoundationRepository techFoundationRepository,
                       TechInteriorRepository techInteriorRepository,
                       TechLadderRepository techLadderRepository,
                       TechOverlapsRepository techOverlapsRepository,
                       TechRoofRepository techRoofRepository) {
        this.techCarrierWallRepository = techCarrierWallRepository;
        this.techExteriorRepository = techExteriorRepository;
        this.techFoundationRepository = techFoundationRepository;
        this.techInteriorRepository = techInteriorRepository;
        this.techLadderRepository = techLadderRepository;
        this.techOverlapsRepository = techOverlapsRepository;
        this.techRoofRepository = techRoofRepository;
    }

    public List<TechCarrierWall> getAllTechCarrierWall() {
        return techCarrierWallRepository.findAll();
    }

    public List<TechExterior> getAllTechExterior() {
        return techExteriorRepository.findAll();
    }

    public List<TechFoundation> getAllTechFoundation() {
        return techFoundationRepository.findAll();
    }

    public List<TechInterior> getAllTechInterior() {
        return techInteriorRepository.findAll();
    }

    public List<TechLadder> getAllTechLadder() {
        return techLadderRepository.findAll();
    }

    public List<TechOverlaps> getAllTechOverlaps() {
        return techOverlapsRepository.findAll();
    }

    public List<TechRoof> getAllTechRoof() {
        return techRoofRepository.findAll();
    }

//    public String getTechCarrierWallWord(Long id) {
//        return techCarrierWallRepository.findById(id).getWord();
//    }
//
//    public String getTechExteriorWord(Long id) {
//        return techExteriorRepository.findById(id).ge();
//    }
//
//    public String getTechFoundationWord(Long id) {
//        return techCarrierWallRepository.findById(id).getWord();
//    }
//
//    public String getTechTechInteriorWord(Long id) {
//        return techCarrierWallRepository.findById(id).getWord();
//    }
//
//    public String getTechLadderWord(Long id) {
//        return techCarrierWallRepository.findById(id).getWord();
//    }
//
//    public String getTechOverlapsWord(Long id) {
//        return techCarrierWallRepository.findById(id).getWord();
//    }
//
//    public String getTechRoofWord(Long id) {
//        return techCarrierWallRepository.findById(id).getWord();
//    }
}
