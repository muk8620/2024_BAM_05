package com.koreaIT.BAM.controller;

import java.util.Scanner;

import com.koreaIT.BAM.dto.Member;

public abstract class Controller {
	
	public Scanner sc;
	public String cmd;
	public Member loginedMember;
	
	public abstract void doAction(String cmd, String methodName);
	public abstract void makeTestData();
}