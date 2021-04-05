package dao.quiz;

import java.io.Serializable;

public class Quiz implements Serializable{

	private static final long serialVersionUID = 6549880068360627432L;
	private String quizContents;
	private String quizAnswer;
	private String receiver;
	private String sender;
	
	public Quiz()
	{
		
	}
	public Quiz(String quizContents, String quizAnswer, String receiver,
			String sender) {
		this.quizContents = quizContents;
		this.quizAnswer = quizAnswer;
		this.receiver = receiver;
		this.sender = sender;
	}

	public String getQuizContents() {
		return quizContents;
	}
	public void setQuizContents(String quizContents) {
		this.quizContents = quizContents;
	}
	public String getQuizAnswer() {
		return quizAnswer;
	}
	public void setQuizAnswer(String quizAnswer) {
		this.quizAnswer = quizAnswer;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public boolean checkAnswer(String memberAnswer)
	{
		if(this.quizAnswer.equals(memberAnswer))
			return true;
		else
			return false;
	}
	@Override
	public String toString() {
		return "Quiz [quizContents=" + quizContents + ", quizAnswer="
				+ quizAnswer + ", receiver=" + receiver + ", sender=" + sender
				+ "]";
	}

}
