package model;

public enum TicToe {
	X(1),O(2);
	private Integer value;

	TicToe(int i) {
		this.value = i;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
