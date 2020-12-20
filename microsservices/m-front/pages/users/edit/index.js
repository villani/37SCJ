import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Link from 'next/link';

function Edit({ usuario }) {
  return (
    <Container>
        <Row>
            <Col>
              <h1>Inserção de Usuário</h1>
              <p><Link href="/users">Cancelar</Link></p>
              <Form>
                <Form.Group controlId="name">
                  <Form.Label>Nome:</Form.Label>
                  <Form.Control type="text" placeholder="Informe o nome do usuário." />
                </Form.Group>

                <Form.Group controlId="email">
                  <Form.Label>E-mail</Form.Label>
                  <Form.Control type="email" placeholder="Informe o e-mail." />
                </Form.Group>

                <Form.Group controlId="password">
                  <Form.Label>Senha:</Form.Label>
                  <Form.Control type="password" placeholder="Senha" />
                </Form.Group>

                <Form.Group controlId="maritalStatus">
                  <Form.Label>Estado civil:</Form.Label>
                  <Form.Control as="select" defaultValue="Solteiro(a)">
                    <option>Solteiro(a)</option>
                    <option>Casado(a)</option>
                    <option>Divorciado(a)</option>
                    <option>União estável</option>
                  </Form.Control>
                </Form.Group>

                <Form.Group controlId="birthDate">
                  <Form.Label>Data de nascimento:</Form.Label>
                  <Form.Control type="date" />
                </Form.Group>

                <Form.Group controlId="allowNotifications">
                  <Form.Check
                    required
                    label="Permitir notificações"
                    feedback="Você precisa permitir se desejar ser notificado."
                  />
                </Form.Group>
               
                <Button variant="primary" type="submit">
                  Gravar
                </Button>
              </Form>
            </Col>
        </Row>
    </Container>
  )
}

export default Edit