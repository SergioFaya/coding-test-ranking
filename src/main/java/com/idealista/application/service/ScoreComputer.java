package com.idealista.application.service;

/**
 * Interface for those {@link com.idealista.application.model.ad.Ad} which can compute an Score
 */
public interface ScoreComputer {

    /**
     * Computes the score of the ad
     */
    int computeScore();

}
