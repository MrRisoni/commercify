<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableLanguages extends AbstractMigration
{

    public function change(): void
    {
        $languages = $this->table('languages', ['signed' => false]);
        $languages->addColumn('title', 'string', ['limit' => 55])
            ->addColumn('code', 'string', ['limit' => 5])
            ->addIndex(['code'], ['unique' => true])
            ->create();
    }
}
