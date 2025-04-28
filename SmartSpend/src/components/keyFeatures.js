import { Card, Col, Row } from 'react-bootstrap';
import { FiRefreshCw, FiPieChart, FiAlertCircle, FiShield, FiDollarSign } from 'react-icons/fi';

const KeyFeatures = () => {
  const features = [
    {
      icon: <FiRefreshCw size={24} className="text-success" />,
      title: "Real-time Transaction Tracking",
      description: "Automatically imports and categorizes transactions from linked bank accounts."
    },
    {
      icon: <FiDollarSign size={24} className="text-success" />,
      title: "Personalized Budgeting",
      description: "Create custom budgets for different spending categories."
    },
    {
      icon: <FiPieChart size={24} className="text-success" />,
      title: "Actionable Insights",
      description: "Gain insights into spending patterns with clear visualizations."
    },
    {
      icon: <FiAlertCircle size={24} className="text-success" />,
      title: "Customizable Alerts",
      description: "Receive notifications for nearing budget limits or unusual spending."
    },
    {
      icon: <FiShield size={24} className="text-success" />,
      title: "Secure and Private",
      description: "Employs industry-standard security practices to protect user data."
    }
  ];

  return (
    <div className="bg-success p-5 mt-5">
      <h2 className="text-center mb-5 s-header">Key Features</h2>
      <Row xs={1} md={2} lg={3} className="g-4">
        {features.map((feature, index) => (
          <Col key={index}>
            <Card className="h-100 border-0 shadow-sm">
              <Card.Body className="text-center">
                <div className="mb-3">{feature.icon}</div>
                <Card.Title>{feature.title}</Card.Title>
                <Card.Text>{feature.description}</Card.Text>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </div>
  );
};

export default KeyFeatures;  // Changed to default export