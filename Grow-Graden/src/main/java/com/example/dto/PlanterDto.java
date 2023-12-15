package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanterDto {
	private Integer planterId;
	private Float planterHeight;
	private Integer planterCapacity;
	private Integer drinageHoles;
	private String planterColour;
	private String planterShape;
	private Integer planterStock;
	private Double planterCost;
	private String planterImageUrl;
	
	 /**
	 * {
	        "planterId": 7,
	        "planterHeight": 70.0,
	        "planterCapacity": 800,
	        "drinageHoles": 80,
	        "planterColour": "alue",
	        "planterImageUrl": "ajsdlf'jlasdfjlsfjlsdfja;slfdjlf",
	        "planterShape": "lquare",
	        "planterStock": 110,
	        "planterCost": 2000.0
    	}
	 */
}
