function rotate(matrix: number[][]): void {
    const n = matrix.length;

    for (let layer = 0; layer < n / 2; layer++) {
        let first = layer;
        let last = n - 1 - layer;

        for (let i = first; i < last; i++) {
            let offset = i - first;

            let top = matrix[first][i];

            matrix[first][i] = matrix[last - offset][first];
            matrix[last - offset][first] = matrix[last][last - offset];
            matrix[last][last - offset] = matrix[i][last];
            matrix[i][last] = top;
        }
    }
}

// Test
let matrix: number[][] = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
];

let output = ">>> CTCI Chapter 1.7 – Rotate Matrix <<<br><br>";
output += "Original:<br>" + JSON.stringify(matrix) + "<br><br>";

rotate(matrix);

output += "Rotated:<br>" + JSON.stringify(matrix);

(document.querySelector('#t1') as HTMLElement).innerHTML = output;