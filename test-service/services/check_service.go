package services

import (
	"test-service/spec/check"
)

type CheckService struct{}

func (service *CheckService) CheckEmpty() error {
	return nil
}
func (service *CheckService) CheckForbidden() (*check.CheckForbiddenResponse, error) {
	return &check.CheckForbiddenResponse{Forbidden: &check.Empty}, nil
}

func (service *CheckService) SameOperationName() (*check.SameOperationNameResponse, error) {
	return &check.SameOperationNameResponse{Ok: &check.Empty}, nil
}
