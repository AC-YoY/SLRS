package princeton;

public class LCS {
	public static void main(String[] args) {
		LCS lcs = new LCS();
		lcs.getLCS("ASDFSDGSDGH", "SDF");

	}

	private static int[][] r;

	public void getLCS(String x, String y) {
		int m = x.length();
		int n = y.length();
		r = new int[m + 1][n + 1];
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				if (x.charAt(i) == y.charAt(j)) {
					r[i][j] = r[i + 1][j + 1] + 1;
				} else {
					r[i][j] = Math.max(r[i + 1][j], r[i][j + 1]);
				}
			}
		}

		int i = 0, j = 0;
		while (i < m && j < n) {
			if (x.charAt(i) == y.charAt(j)) {
				System.out.print(x.charAt(i));
				i++;
				j++;
			} else {
				if (r[i + 1][j] >= r[i][j + 1]) {
					i++;
				} else {
					j++;
				}
			}
		}
		System.out.println();
	}
}
