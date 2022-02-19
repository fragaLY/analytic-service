import { useState } from "react";
import "./App.css";

function App() {
  const [state, setState] = useState({
    data: null,
    loading: false,
  });

  const handleAggregateData = async () => {
    setState({ ...state, loading: true });
    const url = "http://localhost/api/teams/aggregate";
    const response = await fetch(url, {
      method: "GET",
    });
    const result = await response.json();
    setState({ ...state, data: result, loading: false });
  };

  const { loading, data } = state;

  return (
    <div className="app">
      <div className="dashboard">
        <div className="dashboard__content">
          <div className="dashboard__data">
            {loading && <div className="loader"></div>}
            {!loading && data && (
              <div className="dashboard__data-wrapper">
                <div className="dashboard__data-values">
                  <div className="dashboard__data-key">Most Win:</div>
                  <span className="dashboard__data-value">
                    {`${data?.mostWin?.team}: `}
                    <span className="dashboard__data-amount">
                      {`${data?.mostWin?.amount} games`}
                    </span>
                  </span>
                </div>
                <div className="dashboard__data-values">
                  <div className="dashboard__data-key">
                    Most Scored Per Game:
                  </div>
                  <span className="dashboard__data-value">
                    {`${data?.mostScoredPerGame?.team}: `}
                    <span className="dashboard__data-amount">
                      {`${data?.mostScoredPerGame?.amount} goals`}
                    </span>
                  </span>
                </div>
                <div className="dashboard__data-values">
                  <div className="dashboard__data-key">
                    Less Received Per Game:
                  </div>
                  <span className="dashboard__data-value">
                    {`${data?.lessReceivedPerGame?.team}: `}
                    <span className="dashboard__data-amount">
                      {`${data?.lessReceivedPerGame?.amount} goals`}
                    </span>
                  </span>
                </div>
              </div>
            )}
            {!loading && !data && (
              <div className="dashboard__no-data">No data</div>
            )}
          </div>
          <button className="agg-button" onClick={handleAggregateData}>
            Aggregate
          </button>
        </div>
      </div>
    </div>
  );
}

export default App;
