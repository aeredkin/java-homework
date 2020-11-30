import './App.css';
import React from "react";

const Cell = ({mark, onMarkPlace, row, column}) => {
    const handleClick = () => {
        if (!mark) {
            onMarkPlace(row, column);
        }
    }
    return (
        <div className="Cell" onClick={handleClick}>
            {mark}
        </div>
    );
}

function Row({marks, onMarkPlace, row}) {
    const cells = marks.map((mark, column) =>
        <Cell key={`${row}|${column}`} mark={mark} onMarkPlace={onMarkPlace}
              row={row} column={column}/>
    );
    return (
        <div className="Row">
            {cells}
        </div>
    );
}

function Rows({marks, onMarkPlace}) {
    const rows = marks.map((marks, row) =>
        <Row key={row.toString()} marks={marks} onMarkPlace={onMarkPlace} row={row}/>
    );
    return (
        <div className="Rows">
            {rows}
        </div>
    );
}

function determineWinner(marks) {
    let fullGrid = true;
    for (let i = 0; i < marks.length; ++i) {
        if (marks[i][0] !== "" && marks[i][0] === marks[i][1] && marks[i][1] === marks[i][2]) {
            return marks[i][0];
        }
        if (marks[0][i] !== "" && marks[0][i] === marks[1][i] && marks[1][i] === marks[2][i]) {
            return marks[0][i];
        }
        fullGrid = fullGrid && !marks[i].includes("");
    }
    if (marks[0][0] !== "" && marks[0][0] === marks[1][1] && marks[1][1] === marks[2][2]) {
        return marks[0][0];
    }
    if (marks[0][2] !== "" && marks[0][2] === marks[1][1] && marks[1][1] === marks[2][0]) {
        return marks[0][2];
    }
    if (fullGrid) {
        return "xo";
    }
}

function winMessage(winner) {
    let message;
    if (winner === "xo") {
        message = "Ничья";
    } else {
        message = "Выиграл " + winner;
    }
    return message;
}

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {marks: [["", "", ""], ["", "", ""], ["", "", ""]], winner: "", currentMark: "x"};
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        this.setState({marks: [["", "", ""], ["", "", ""], ["", "", ""]], winner: "", currentMark: "x"});
    }

    render() {
        return (
            <div className="App">
                <div className="Button" onClick={this.handleClick}>Новая игра</div>
                <Rows marks={this.state.marks} onMarkPlace={(row, column) => {
                    if (!this.state.winner && !this.state.marks[row][column]) {
                        const newRow = [...this.state.marks[row]];
                        const marks = [...this.state.marks];
                        newRow[column] = this.state.currentMark;
                        marks[row] = newRow;
                        this.setState({marks});
                        this.setState({winner: determineWinner(marks)});
                        this.setState({currentMark: this.state.currentMark === "x" ? "o" : "x"});
                    }
                }}/>
                {!this.state.winner && <div>Ход игрока {this.state.currentMark}</div>}
                {this.state.winner && <div>{winMessage(this.state.winner)}</div>}
            </div>
        );
    }
}

export default App;
