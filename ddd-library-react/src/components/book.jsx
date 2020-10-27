import React, { Component } from "react";
class book extends Component {
  state = {
    books: []
  };

  async componentDidMo11unt() {
    const response = await fetch('/books');
    const body = await response.json();
    this.setState({ books: body });
  }


  render() {
    const groups = this.state.books;

    return (
        <div className="App">
          <header className="App-header">
            {/*<img src={logo} className="App-logo" alt="logo" />*/}
            <div className="App-intro">
              <h2>Book List</h2>
              {groups.map(group =>
                  <div key={group.id}>
                    {group.name}
                  </div>
              )}
            </div>
          </header>
        </div>
    );
  }
}

export default book;
