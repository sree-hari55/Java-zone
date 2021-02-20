package com.javazone.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest implements Serializable{


	private static final long serialVersionUID = 1L;
	
	protected long postId;
	
	protected String title;
	
	protected String content;
	
	protected String userName;
		
}
