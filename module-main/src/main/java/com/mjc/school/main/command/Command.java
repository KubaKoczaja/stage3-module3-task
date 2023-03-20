package com.mjc.school.main.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Command {
		private int id;
		private Long param;
		private String body;
}