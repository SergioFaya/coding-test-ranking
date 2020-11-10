package com.idealista.application.service;

import com.idealista.application.model.Picture;

import java.util.List;

/**
 * Defines the strategy template which must be followed by all score computing services
 */
public interface ScoreComputerStrategy {

    /**
     * Defines the operation of computing an score
     *
     * @return
     */
    int computeScore(String description, Integer size, List<Picture> pictures, boolean isComplete);


}
