package v2

import "test-service/spec/v2"

type EchoService struct{}

func (service *EchoService) EchoBody(body *v2.Message) (*v2.EchoBodyResponse, error) {
	return &v2.EchoBodyResponse{Ok: body}, nil
}
