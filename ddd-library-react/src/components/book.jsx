import React, { Component } from "react";
class book extends Component {
  state = {
    bookName: "Factfulness",
    author: "Hans Rosling",
    available: "yes",
  };
  render() {
    return (
      <div>
        <h1>Library Books</h1>

        <table>
          <tr>
            <th>Book Name</th>
            <th>Author</th>
            <th>Available</th>
          </tr>
          <tr>
            <td>{this.state.bookName}</td>
            <td>{this.state.author}</td>
            <td>{this.state.available}</td>
          </tr>
        </table>
      </div>
    );
  }
}

export default book;
