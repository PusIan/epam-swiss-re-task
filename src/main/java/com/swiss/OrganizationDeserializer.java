package com.swiss;

import com.swiss.model.OrganizationTree;

import java.util.List;

public interface OrganizationDeserializer {
    OrganizationTree deserialize(List<String> input);
}
