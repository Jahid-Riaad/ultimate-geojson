package org.adv.gis.exception;


import org.adv.gis.model.PositionDto;

public class InvalidPositionDtoException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;

	public InvalidPositionDtoException(PositionDto position) {
		super("Invalid position dto: " + position);
	}

}
