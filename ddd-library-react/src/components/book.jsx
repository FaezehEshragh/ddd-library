import React, { Component } from "react";
class book extends Component {
  state = {
    books: []
  };

  async componentDidMount() {
    const response = await fetch('/books');
    const body = await response.json();
    this.setState({ books: body });
  }


  render() {
    const books = this.state.books;

    return (
        <div className="App">
          <header className="App-header">
            <div className="App-intro">
              <h2>Book List</h2>
              {books.map(book =>
                  <div key={book.id}>
                    {book.name}
                  </div>
              )}
            </div>
          </header>
        </div>
    );
  }
}

export default book;
