package ru.galeev.springapp.utils.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.galeev.springapp.utils.filter.contentbased.ContentBasedFilter;
import ru.galeev.springapp.utils.filter.itembased.ItemBasedFilter;

@Service
public class RecomendSystem {

    @Autowired
    ContentBasedFilter cbFilter;
    @Autowired
    ItemBasedFilter ibFilter;


}
